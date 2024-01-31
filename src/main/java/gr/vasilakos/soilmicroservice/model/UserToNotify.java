package gr.vasilakos.soilmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user-to-notify")
public class UserToNotify {
    @Id
    private String id;
    private String email;
    private String coordinates;

    private Double lessSoilTemperature;
    private Double moreSoilTemperature;

    private Double lessSoilMoisture;
    private Double moreSoilMoisture;

    private Double lessElectronicConductivity;
    private Double moreElectronicConductivity;

    private Double lessVolumetricWaterContent;
    private Double moreVolumetricWaterContent;

    private Double lessSalinity;
    private Double moreSalinity;

    private Double lessTotalSuspendedSolids;
    private Double moreTotalSuspendedSolids;

    private Double lessFlowVolume;
    private Double moreFlowVolume;

    private Double lessNitrate;
    private Double moreNitrate;
}

