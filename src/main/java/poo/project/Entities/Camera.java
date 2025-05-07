package poo.project.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import poo.project.Enums.CameraStatus;

import java.util.ArrayList;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Camera {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String videoStreamUrl;
    @ManyToMany
    private List<Zone> zones = new ArrayList<>();
    private CameraStatus cameraStatus;

}
