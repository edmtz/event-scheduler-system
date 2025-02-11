package com.edmtz.repository;

import com.edmtz.model.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e LEFT JOIN FETCH e.participants WHERE e.id = :eventId")
    Optional<Event> findByIdWithParticipants(@Param("eventId") Long eventId);

    @Query("SELECT u.id FROM Event e JOIN e.participants u WHERE e.id = :eventId")
    List<Long> findParticipantIdsByEventId(@Param("eventId") Long eventId);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Event e WHERE e.id = :eventId AND e.createdBy.username = :username")
    boolean isUserOwnerOfEvent(@Param("eventId") Long eventId, @Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM event_participants WHERE event_id = :eventId AND user_id = :userId", nativeQuery = true)
    void removeUserFromEvent(@Param("eventId") Long eventId, @Param("userId") Long userId);

}
