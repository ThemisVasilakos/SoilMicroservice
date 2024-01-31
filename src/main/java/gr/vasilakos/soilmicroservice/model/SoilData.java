package gr.vasilakos.soilmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "soil-data")
public class SoilData {

    private double soilTemperature;
    private double soilMoisture;
    private double electronicConductivity;
    private double volumetricWaterContent;
    private double salinity;
    private double totalSuspendedSolids;
    private double flowVolume;
    private double nitrate;
    @CreatedDate
    private Instant timestamp;
    private String coordinates;
}
