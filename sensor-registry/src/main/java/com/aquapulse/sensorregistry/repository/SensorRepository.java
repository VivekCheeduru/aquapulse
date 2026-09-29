package com.aquapulse.sensorregistry.repository;

import com.aquapulse.sensorregistry.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends  JpaRepository<Sensor,String> {

}
