package proj.eval.app.entity;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "quote_details")
public class QuoteDetail extends Repository<QuoteDetail, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "quote_id")
  private Long quoteId;

  @Column(name = "work_details_id")
  private Long workDetailsId;

  @Column
  private Double quantity;

  @Column
  private Double unitPrice;
}
