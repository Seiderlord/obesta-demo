package at.example.backenddemo.model.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;
/**
 * The "MediaResource" class represents media resource of an item.
 *
 * @author Bert Ödemis
 */
@Data
@Entity(name = "media_resource")
@Table(name = "media_resource",schema = "demo")
@AllArgsConstructor
@NoArgsConstructor
public class MediaResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Lob
    @Fetch(FetchMode.SELECT)
    @Column(nullable = false)
    @NotNull
    private Blob data;

    @Column(nullable = false)
    @NotNull
    private String fileName;
    @Column(nullable = false)
    @NotNull
    private String contentType;

    public MediaResource(MultipartFile file) throws IOException, SQLException {
        this.data = new SerialBlob(file.getBytes());
        this.fileName = file.getName();
        this.contentType = file.getContentType();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MediaResource) {
            return Objects.equals(((MediaResource) obj).getId(), getId());
        }
        return false;
    }

}
