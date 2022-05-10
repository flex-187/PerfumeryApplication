package ok.perfumery.converter;
import org.springframework.core.convert.converter.Converter;

import ok.perfumery.entity.Product;
public class ProductTypeToStringConverter 
			implements Converter<Product.Type,String>{

	@Override
	public String convert(Product.Type source) {
		return source.name();
	}

}
