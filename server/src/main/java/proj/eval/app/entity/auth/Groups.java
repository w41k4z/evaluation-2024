package proj.eval.app.entity.auth;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import lombok.Getter;
import lombok.Setter;
import proj.eval.app.enumeration.Roles;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;
import proj.w41k4z.orm.database.request.Condition;
import proj.w41k4z.orm.database.request.Operator;

@Getter
@Setter
@Entity(table = "groups")
public class Groups extends Repository<Groups, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "group_name")
  private String groupName;

  public Groups findByGroupName(DatabaseConnection connection, Roles groupName)
    throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IllegalArgumentException, SecurityException, ClassNotFoundException, SQLException, IOException {
    return findOne(
      connection,
      Condition.WHERE("_group_name_", Operator.E, groupName)
    );
  }
}
