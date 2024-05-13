package proj.eval.app.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import proj.eval.app.entity.FinitionType;
import proj.eval.app.entity.HouseType;

@Data
public class NewQuoteRequest {

  @NotNull(message = "The house type is required")
  private HouseType houseType;

  @NotNull(message = "The construction starting date is required")
  private LocalDate constructionStartDate;

  @NotNull(message = "The finition type is required")
  private FinitionType finitionType;
}
