package study.rtmppractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.rtmppractice.service.FileServerService;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = RequestMethod.GET)
public class FileServerController {

    private final FileServerService service;

    @GetMapping("/stream/{info}")
    public ResponseEntity<ResourceRegion> streamingPublicVideo(@RequestHeader HttpHeaders httpHeaders, @PathVariable String info){
        return service.streamingPublicVideo(httpHeaders, info);
    }
}
