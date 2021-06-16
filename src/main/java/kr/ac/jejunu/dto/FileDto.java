package kr.ac.jejunu.dto;

import kr.ac.jejunu.controller.File;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String filename;
    private String filePath;

    public File toEntity() {
        File build = File.builder()
                .id(id)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, String filename, String filePath) {
        this.id = id;
        this.filename = filename;
        this.filePath = filePath;
    }
}
