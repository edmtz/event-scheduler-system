package com.edmtz.repository;

import com.edmtz.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Event e WHERE e.id = :eventId AND e.createdBy.username = :username")
    boolean isUserOwnerOfEvent(@Param("eventId") Long eventId, @Param("username") String username);

}
