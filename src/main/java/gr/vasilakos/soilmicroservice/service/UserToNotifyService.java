package gr.vasilakos.soilmicroservice.service;

import gr.vasilakos.soilmicroservice.dto.UserToNotifyDto;
import gr.vasilakos.soilmicroservice.dto.SoilQueueDataDto;
import gr.vasilakos.soilmicroservice.dto.SoilQueueNotifyUsersDto;
import gr.vasilakos.soilmicroservice.model.UserToNotify;
import gr.vasilakos.soilmicroservice.model.SoilData;
import gr.vasilakos.soilmicroservice.repository.UserToNotifyRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserToNotifyService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.soil.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;
    private final UserToNotifyRepository userToNotifyRepository;

    public UserToNotifyService(UserToNotifyRepository userToNotifyRepository, RabbitTemplate rabbitTemplate) {
        this.userToNotifyRepository = userToNotifyRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserToNotifyDto userSubToSoilData(UserToNotifyDto userToNotifyDto){
        userToNotifyRepository.save(mapUserToNotifyDtoToUserToNotify(userToNotifyDto));
        return userToNotifyDto;
    }

    public void deleteUser(String email){
        List<UserToNotify> userToNotifyList = userToNotifyRepository.findByEmail(email);
        userToNotifyRepository.deleteAll(userToNotifyList);
    }


    public void getNotifyUserList(SoilData soilData){
        List<UserToNotify> userToNotifyList = userToNotifyRepository.findByCoordinatesAndLessSoilTemperature(soilData.getCoordinates(), soilData.getSoilTemperature());
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreSoilTemperature(soilData.getCoordinates(), soilData.getSoilTemperature()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessSoilMoisture(soilData.getCoordinates(), soilData.getSoilMoisture()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreSoilMoisture(soilData.getCoordinates(), soilData.getSoilMoisture()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessElectronicConductivity(soilData.getCoordinates(), soilData.getElectronicConductivity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreElectronicConductivity(soilData.getCoordinates(), soilData.getElectronicConductivity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessVolumetricWaterContent(soilData.getCoordinates(), soilData.getVolumetricWaterContent()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreVolumetricWaterContent(soilData.getCoordinates(), soilData.getVolumetricWaterContent()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessSalinity(soilData.getCoordinates(), soilData.getSalinity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreSalinity(soilData.getCoordinates(), soilData.getSalinity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessTotalSuspendedSolids(soilData.getCoordinates(), soilData.getTotalSuspendedSolids()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreTotalSuspendedSolids(soilData.getCoordinates(), soilData.getTotalSuspendedSolids()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessFlowVolume(soilData.getCoordinates(), soilData.getFlowVolume()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreFlowVolume(soilData.getCoordinates(), soilData.getFlowVolume()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessNitrate(soilData.getCoordinates(), soilData.getNitrate()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreNitrate(soilData.getCoordinates(), soilData.getNitrate()));

        List<String> emails = userToNotifyList.stream()
                .map(UserToNotify::getEmail).toList();

        SoilQueueNotifyUsersDto waterQueueNotifyUsersDto = new SoilQueueNotifyUsersDto();
        waterQueueNotifyUsersDto.setSoilData(mapWaterDataToWaterQueueDataDto(soilData));
        waterQueueNotifyUsersDto.setEmails(emails);

        if(!emails.isEmpty()){
            rabbitTemplate.convertAndSend(exchange,routingKey, waterQueueNotifyUsersDto);
        }

    }

    public SoilQueueDataDto mapWaterDataToWaterQueueDataDto(SoilData soilData){
        return SoilQueueDataDto.builder()
                .soilTemperature(soilData.getSoilTemperature())
                .soilMoisture(soilData.getSoilMoisture())
                .electronicConductivity(soilData.getElectronicConductivity())
                .volumetricWaterContent(soilData.getVolumetricWaterContent())
                .salinity(soilData.getSalinity())
                .totalSuspendedSolids(soilData.getTotalSuspendedSolids())
                .flowVolume(soilData.getFlowVolume())
                .nitrate(soilData.getNitrate())
                .timestamp(soilData.getTimestamp().toString())
                .coordinates(soilData.getCoordinates())
                .build();
    }

    private UserToNotify mapUserToNotifyDtoToUserToNotify(UserToNotifyDto userToNotifyDto){
        return UserToNotify.builder()
                .email(userToNotifyDto.getEmail())
                .coordinates(userToNotifyDto.getCoordinates())
                .lessSoilTemperature(userToNotifyDto.getLessSoilTemperature())
                .moreSoilTemperature(userToNotifyDto.getMoreSoilTemperature())
                .lessSoilMoisture(userToNotifyDto.getLessSoilMoisture())
                .moreSoilMoisture(userToNotifyDto.getMoreSoilMoisture())
                .lessElectronicConductivity(userToNotifyDto.getLessElectronicConductivity())
                .moreElectronicConductivity(userToNotifyDto.getMoreElectronicConductivity())
                .lessVolumetricWaterContent(userToNotifyDto.getLessVolumetricWaterContent())
                .moreVolumetricWaterContent(userToNotifyDto.getMoreVolumetricWaterContent())
                .lessSalinity(userToNotifyDto.getLessSalinity())
                .moreSalinity(userToNotifyDto.getMoreSalinity())
                .lessTotalSuspendedSolids(userToNotifyDto.getLessTotalSuspendedSolids())
                .moreTotalSuspendedSolids(userToNotifyDto.getMoreTotalSuspendedSolids())
                .lessFlowVolume(userToNotifyDto.getLessFlowVolume())
                .moreFlowVolume(userToNotifyDto.getMoreFlowVolume())
                .lessNitrate(userToNotifyDto.getLessNitrate())
                .moreNitrate(userToNotifyDto.getMoreNitrate())
                .build();
    }



}
