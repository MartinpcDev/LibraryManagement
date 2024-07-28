package com.martin.projects.Library.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martin.projects.Library.util.ValidGender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveBook {

  @NotBlank(message = "El campo title es obligatorio")
  @Size(min = 5, max = 50, message = "El campo title debe tener entre 2 y 30 caracteres")
  private String title;

  @NotBlank(message = "El campo isbn es obligatorio")
  @Pattern(regexp = "^(?:ISBN(?:-10)?:? )?(?=[0-9]{9}X|[0-9]{10}$)(?:[0-9]{1,5}[- "
      + "])?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$|^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$)(?:[0-9]{1,5}[- "
      + "])?[0-9]+[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$",
      message = "El campo ISBN debe estar en un formato válido")
  private String isbn;

  @JsonProperty(value = "release_year")
  @NotNull(message = "El campo release_year es obligatorio")
  @Min(value = 1200)
  @Max(value = 2024)
  private Integer publicationYear;

  @NotBlank(message = "El campo gender es obligatorio")
  @ValidGender(message = "El campo gender debe ser uno de los valores permitidos: "
      + "SUSPENSE,NOVELA_HISTORICA,ROMANTICA,CIENCIA_FICCION,DISTOPIA,AVENTURAS,\n"
      + "  FANTASIA,CONTEMPORANEO,TERROR,PARANORMAL,POESIA,JUVENIL,INFANTIL,\n"
      + "  AUTOAYUDA,SALUD,MANUALIDADES,MEMORIAS,BIOGRAFIAS,COCINA,VIAJES,TECNICOS,\n"
      + "  TEXTO,ARTE")
  private String gender;

  @NotNull(message = "El campo stock es obligatorio")
  @Min(value = 20)
  @Max(value = 200)
  private Integer stock;

  @JsonProperty(value = "author_id")
  @NotNull(message = "El campo author_id es obligatorio")
  private Long authorId;

  @JsonProperty(value = "editorial_id")
  @NotNull(message = "El campo editorial_id es obligatorio")
  private Long editorialId;
}
