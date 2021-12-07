package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long>{

	ItemImage findByItemId(long id);
	
}
