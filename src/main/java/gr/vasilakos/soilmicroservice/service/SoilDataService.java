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

}
