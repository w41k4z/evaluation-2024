package proj.eval.app.entity;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.annotation.ReadOnly;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "v_quote_details")
@ReadOnly
public class V_QuoteDetail extends Repository<V_QuoteDetail, Long> {

  // This fix the ORM bug
  @Id
  @Column(name = "row_number")
  private Integer rowNumber;

  @Column
  private Long id;

  @Column(name = "action_date")
  private Date date;

  @Column(name = "users_id")
  private Long userId;

  @Column(name = "works_name")
  private String workName;

  @Column(name = "work_details_designation")
  private String workDetailDesignation;

  @Column(name = "units_name")
  private String unit;

  @Column
  private Double quantity;

  @Column(name = "unit_price")
  private Double unitPrice;
}
