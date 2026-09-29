package com.aquapulse.sensorsimulator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "aquapulse")
@Getter
@Setter
public class SensorProperties {
    private SimulationProperties simulation;
    private List<SensorConfiguration> sensors;
}
