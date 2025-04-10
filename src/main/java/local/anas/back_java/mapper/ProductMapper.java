package local.anas.back_java.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import local.anas.back_java.model.Product;

@Mapper
public interface ProductMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateProductFromDto(@MappingTarget Product product, Product productDto);

}
