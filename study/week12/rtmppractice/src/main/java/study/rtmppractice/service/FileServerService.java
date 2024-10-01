package study.rtmppractice.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Service
public class FileServerService {
    public ResponseEntity<ResourceRegion> streamingPublicVideo(HttpHeaders httpHeaders, String pathStr) {
        try {
            Path path = Paths.get(pathStr);
            Resource resource = new FileSystemResource(path);
            long chunkSize = 1024 * 1024; // 청크 크기를 1MB
            long contentLength = resource.contentLength(); // 리소스의 전체 길이
            ResourceRegion resourceRegion;
            try {
                HttpRange httpRange;
                if (httpHeaders.getRange().stream().findFirst().isPresent()) { // Range 헤더가 존재하는지 확인
                    httpRange = httpHeaders.getRange().stream().findFirst().get(); // 첫 번째 Range 헤더 겟
                    long start = httpRange.getRangeStart(contentLength); // Range 의 시작 위치를 계산
                    long end = httpRange.getRangeEnd(contentLength); // Range 의 끝 위치를 계산
                    long rangeLength = Long.min(chunkSize, end - start + 1); // 청크 크기와 Range 길이 중 작은 값을 선택, 동영상 마지막 부분일 수도 있기 때문

                    resourceRegion = new ResourceRegion(resource, start, rangeLength); // ResourceRegion 객체를 생성
                } else {
                    resourceRegion = new ResourceRegion(resource, 0, Long.min(chunkSize, resource.contentLength())); // Range 헤더가 없을 경우 처음부터 청크 크기만큼 설정
                }
            } catch (Exception e) {
                long rangeLength = Long.min(chunkSize, resource.contentLength()); // 예외 발생 시 처음부터 청크 크기만큼 설정
                resourceRegion = new ResourceRegion(resource, 0, rangeLength); // ResourceRegion 객체를 생성
            }
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT) // HTTP 상태 코드를 206 (Partial Content)로 설정
                    .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)) // 10초
                    .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM)) // 콘텐츠 타입 APPLICATION_OCTET_STREAM
                    .header(HttpHeaders.ACCEPT_RANGES, "bytes") // Accept-Ranges 헤더를 설정
                    .body(resourceRegion); // 응답 본문에 ResourceRegion 객체를 전송
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
