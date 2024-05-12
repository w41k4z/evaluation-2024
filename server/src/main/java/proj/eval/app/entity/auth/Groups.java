package proj.eval.app.entity.auth;

import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;

@Getter
@Setter
@Entity(table = "groups")
public class Groups extends Repository<Groups, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "group_name")
  private String groupName;
}
