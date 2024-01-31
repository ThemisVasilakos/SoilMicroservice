package gr.vasilakos.soilmicroservice.service;

import gr.vasilakos.soilmicroservice.dto.SoilDataDto;
import gr.vasilakos.soilmicroservice.model.SoilData;
import gr.vasilakos.soilmicroservice.repository.SoilDataRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SoilDataService {

    private final UserToNotifyService userToNotifyService;

    private final SoilDataRepository soilDataRepository;



    public SoilDataService(UserToNotifyService userToNotifyService, SoilDataRepository soilDataRepository) {
        this.userToNotifyService = userToNotifyService;
        this.soilDataRepository = soilDataRepository;
    }

    public List<SoilDataDto> findByParameter(String coordinates, Instant start, Instant end) {
        List<SoilData> res = null;

        res = soilDataRepository.findByTimestampAndCoordinatesOOrderByTimestampAirHumidityAsc(start, end, coordinates);

        return res.stream()
                .map(this::mapSoilDataTSoilDataDto)
                .collect(Collectors.toList());
    }

    public SoilDataDto findLastEntry(String location){
        return mapSoilDataTSoilDataDto(soilDataRepository.findFirstByCoordinatesOrderByTimestampDesc(location));
    }

    private SoilDataDto mapSoilDataTSoilDataDto(SoilData soilData){
        return SoilDataDto.builder()
                .soilTemperature(soilData.getSoilTemperature())
                .soilMoisture(soilData.getSoilMoisture())
                .electronicConductivity(soilData.getElectronicConductivity())
                .volumetricWaterContent(soilData.getVolumetricWaterContent())
                .salinity(soilData.getSalinity())
                .totalSuspendedSolids(soilData.getTotalSuspendedSolids())
                .flowVolume(soilData.getFlowVolume())
                .nitrate(soilData.getNitrate())
                .timestamp(soilData.getTimestamp())
                .coordinates(soilData.getCoordinates())
                .build();
    }
    // Old method for creating data
//    public SoilData createData(){
//        SoilData soilData = SoilData.builder()
//                .soilTemperature(24.0)
//                .soilMoisture(40.0)
//                .electronicConductivity(4.8)
//                .volumetricWaterContent(0.35)
//                .salinity(9.82)
//                .totalSuspendedSolids(18.0)
//                .flowVolume(0.67)
//                .nitrate(5.2)
//                .timestamp(Instant.now())
//                .coordinates("Athens")
//                .build();
//
//       userToNotifyService.getNotifyUserList(soilData);
//        return soilDataRepository.save(soilData);
//    }
//
//    private String getRandomEuropeanCapital() {
//        Random random = new Random();
//        int randomIndex = random.nextInt(europeanCapitals.size());
//        return europeanCapitals.get(randomIndex);
//    }
//
//    private static final List<String> europeanCapitals = Arrays.asList(
//            "Athens", "Berlin", "Paris", "Madrid", "Rome", "London", "Lisbon", "Amsterdam", "Vienna", "Brussels");
}
