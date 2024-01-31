package gr.vasilakos.soilmicroservice.repository;

import gr.vasilakos.soilmicroservice.model.UserToNotify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserToNotifyRepository extends MongoRepository<UserToNotify,String> {

    List<UserToNotify> findByEmail(String email);

    @Query("{'coordinates': ?0, 'lessSoilTemperature': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessSoilTemperature(String coordinates, Double lessSoilTemperature);

    @Query("{'coordinates': ?0, 'moreSoilTemperature': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreSoilTemperature(String coordinates, Double moreSoilTemperature);

    @Query("{'coordinates': ?0, 'lessSoilMoisture': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessSoilMoisture(String coordinates, Double lessSoilMoisture);

    @Query("{'coordinates': ?0, 'moreSoilMoisture': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreSoilMoisture(String coordinates, Double moreSoilMoisture);

    @Query("{'coordinates': ?0, 'lessElectronicConductivity': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessElectronicConductivity(String coordinates, Double lessElectronicConductivity);

    @Query("{'coordinates': ?0, 'moreElectronicConductivity': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreElectronicConductivity(String coordinates, Double moreElectronicConductivity);

    @Query("{'coordinates': ?0, 'lessVolumetricWaterContent': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessVolumetricWaterContent(String coordinates, Double lessVolumetricWaterContent);

    @Query("{'coordinates': ?0, 'moreVolumetricWaterContent': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreVolumetricWaterContent(String coordinates, Double moreVolumetricWaterContent);

    @Query("{'coordinates': ?0, 'lessSalinity': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessSalinity(String coordinates, Double lessSalinity);

    @Query("{'coordinates': ?0, 'moreSalinity': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreSalinity(String coordinates, Double moreSalinity);

    @Query("{'coordinates': ?0, 'lessTotalSuspendedSolids': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessTotalSuspendedSolids(String coordinates, Double lessTotalSuspendedSolids);

    @Query("{'coordinates': ?0, 'moreTotalSuspendedSolids': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreTotalSuspendedSolids(String coordinates, Double moreTotalSuspendedSolids);

    @Query("{'coordinates': ?0, 'lessFlowVolume': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessFlowVolume(String coordinates, Double lessFlowVolume);

    @Query("{'coordinates': ?0, 'moreFlowVolume': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreFlowVolume(String coordinates, Double moreFlowVolume);

    @Query("{'coordinates': ?0, 'lessNitrate': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessNitrate(String coordinates, Double lessNitrate);

    @Query("{'coordinates': ?0, 'moreNitrate': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreNitrate(String coordinates, Double moreNitrate);

}
