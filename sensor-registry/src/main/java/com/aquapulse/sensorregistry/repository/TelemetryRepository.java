package com.aquapulse.sensorregistry.repository;

import com.aquapulse.sensorregistry.models.TelemetryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelemetryRepository extends JpaRepository<TelemetryEvent, UUID> {

}
