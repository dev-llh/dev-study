package study.week15.publisher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.week15.publisher.dto.MqttDto;
import study.week15.publisher.service.MqttService;

@RestController()
@RequestMapping("/mqtt")
@RequiredArgsConstructor
public class MqttController {
    private final MqttService mqttService;

    @PostMapping("/pub")
    public ResponseEntity<Void> publish(@RequestBody MqttDto dto) throws Exception {
        mqttService.publish(dto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/isConnected")
    public ResponseEntity<Boolean> isConnected() throws Exception {
        return ResponseEntity.ok(mqttService.connected());
    }
}
