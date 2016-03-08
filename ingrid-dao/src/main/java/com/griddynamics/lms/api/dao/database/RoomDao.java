package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.naming.Naming;
import com.griddynamics.lms.api.domain.object.employee.location.City;
import com.griddynamics.lms.api.domain.object.employee.location.Room;

import java.util.List;

public interface RoomDao extends IGenericStringIdentifiedDomainDao<Room> {

    @Select("from Room r WHERE r.city = :city")
    List<Room> findRoomsByCity(@Naming("city") City city);

    @Select("from Room r WHERE r.roomId = :roomId")
    Room findRoomById(@Naming("roomId") String id);

    @Select("from Room r ORDER BY r.city ASC")
    List<Room> findAllRoomsOrderedByCityName();
}