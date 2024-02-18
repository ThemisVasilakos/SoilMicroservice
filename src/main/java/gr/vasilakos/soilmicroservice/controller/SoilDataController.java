package gr.vasilakos.soilmicroservice.controller;

import gr.vasilakos.soilmicroservice.dto.SoilDataDto;
import gr.vasilakos.soilmicroservice.model.SoilData;
import gr.vasilakos.soilmicroservice.service.SoilDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/soil")
@RequiredArgsConstructor
public class SoilDataController {

    private final SoilDataService soilDataService;

    @GetMapping("/search-all")
    public ResponseEntity<List<SoilDataDto>> getData(
            @RequestParam String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant end
    ){

        return new ResponseEntity<>(soilDataService.findByParameter(location,start,end), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<SoilDataDto> getData(
            @RequestParam String location
    ){
        return new ResponseEntity<>(soilDataService.findLastEntry(location), HttpStatus.OK);
    }

}
