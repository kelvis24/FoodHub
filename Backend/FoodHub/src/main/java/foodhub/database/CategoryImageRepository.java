package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryImageRepository extends JpaRepository<CategoryImage, Long>{

	CategoryImage findByCategoryId(long id);

}
