package proj.eval.app.entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import lombok.Getter;
import lombok.Setter;
import proj.w41k4z.orm.annotation.Column;
import proj.w41k4z.orm.annotation.Entity;
import proj.w41k4z.orm.annotation.Id;
import proj.w41k4z.orm.database.Repository;
import proj.w41k4z.orm.database.connectivity.DatabaseConnection;

@Getter
@Setter
@Entity(table = "quotes")
public class Quote extends Repository<Quote, Long> {

  @Id
  @Column
  private Long id;

  @Column(name = "\"date\"")
  private Date date;

  @Column(name = "users_id")
  private Long userId;

  @Column(name = "house_types_id")
  private Long houseTypesId;

  @Column(name = "finition_types_id")
  private Long finitionTypesId;

  @Column(name = "finition_type_majoration")
  private Double finitionTypeMajoration;

  @Column(name = "construction_start_date")
  private Date constructionStartDate;

  @Column(name = "construction_end_date")
  private Date constructionEndDate;

  @Column(name = "total_price")
  private Double totalPrice;

  public void create(
    DatabaseConnection connection,
    HouseType houseType,
    FinitionType finitionType,
    Long userId
  )
    throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, SecurityException, IOException, SQLException {
    this.setConstructionStartDate(null);
    //TODO: calculate
    this.setConstructionEndDate(null);
    this.setUserId(userId);
    this.setHouseTypesId(houseType.getId());
    this.setFinitionTypesId(finitionType.getId());
    this.setFinitionTypeMajoration(finitionType.getMajoration());
    //TODO: check if the calcul is true
    this.setTotalPrice(
        houseType.getTotalPrice() +
        (houseType.getTotalPrice() * finitionType.getMajoration())
      );
    this.create(connection);
  }
}
