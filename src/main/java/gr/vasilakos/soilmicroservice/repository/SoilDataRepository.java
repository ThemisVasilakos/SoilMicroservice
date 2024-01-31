package gr.vasilakos.soilmicroservice.repository;

import gr.vasilakos.soilmicroservice.model.SoilData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface SoilDataRepository extends MongoRepository<SoilData, String> {

    SoilData findFirstByCoordinatesOrderByTimestampDesc(String coordinates);
    @Query("{'timestamp': {$gte: ?0, $lte: ?1}, 'coordinates': ?2}")
    List<SoilData> findByTimestampAndCoordinatesOOrderByTimestampAirHumidityAsc(Instant startDate, Instant endDate, String coordinates);
}
