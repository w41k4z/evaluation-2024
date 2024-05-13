package proj.eval.app.entity;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "finition_types")
public class FinitionType extends Repository<FinitionType, Long> {

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private Double majoration;
}
