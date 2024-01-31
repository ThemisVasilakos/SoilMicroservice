package gr.vasilakos.soilmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoilQueueDataDto {

    private double soilTemperature;
    private double soilMoisture;
    private double electronicConductivity;
    private double volumetricWaterContent;
    private double salinity;
    private double totalSuspendedSolids;
    private double flowVolume;
    private double nitrate;
    private String timestamp;
    private String coordinates;
}
