package proj.eval.app.entity;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "work_details")
public class WorkDetail extends Repository<WorkDetail, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "works_id")
  private Long workId;

  @Column
  private String designation;

  @Column(name = "units_id")
  private Long unitId;

  @Column
  private Double price;
}
