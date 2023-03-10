package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmImageRepository extends JpaRepository<FirmImage, Long> {

	FirmImage findByFirmId(long id);
	
	void deleteById(long id);
	
}
