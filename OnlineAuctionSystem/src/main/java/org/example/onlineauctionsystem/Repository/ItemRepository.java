package org.example.onlineauctionsystem.Repository;

import org.example.onlineauctionsystem.Entity.Item;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

}
