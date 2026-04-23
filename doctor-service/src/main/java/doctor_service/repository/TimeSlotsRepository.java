package doctor_service.repository;

import doctor_service.entity.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TimeSlotsRepository extends JpaRepository<TimeSlots, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
UPDATE TimeSlots t
SET t.isBooked = true
WHERE t.id = :slotId AND t.isBooked = false
""")
    int bookSlotIfAvailable(Long slotId);
}