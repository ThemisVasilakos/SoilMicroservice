package gr.vasilakos.soilmicroservice.controller;

import gr.vasilakos.soilmicroservice.dto.UserToNotifyDto;
import gr.vasilakos.soilmicroservice.service.UserToNotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soil")
@RequiredArgsConstructor
public class UserToNotifyController {

    private final UserToNotifyService userToNotifyService;

    @PostMapping("/notify")
    public ResponseEntity<UserToNotifyDto> userSubToWaterData(@RequestBody UserToNotifyDto userToNotifyDto){
        return new ResponseEntity<>(userToNotifyService.userSubToSoilData(userToNotifyDto), HttpStatus.OK);
    }

    @DeleteMapping ("/notify")
    public void userSubToWaterData(@RequestParam String email){
        userToNotifyService.deleteUser(email);
    }
}
