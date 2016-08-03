package com.jrtech.templates.services;

import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jiangliang on 2016/6/29.boostrap-table 参数实体转换成jpa分页实体，elus
 */
public class PageableImpl implements Pageable {
	private TableGetDataParameters parameters;

	public PageableImpl(TableGetDataParameters parameters) {
		this.parameters = parameters;
	}

	@Override
	public int getPageNumber() {
		return parameters.getPageNumber();
	}

	@Override
	public int getPageSize() {
		return parameters.getLimit();
	}

	@Override
	public int getOffset() {
		return parameters.getOffset();
	}

	@Override
	public Sort getSort() {
		return buildSort();
	}

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}
	public Sort buildSort(){
		if(parameters.getSort()==null){
			return null;
		}
		List<Sort.Order> orders=new ArrayList<>();
		Sort.Direction direction= Sort.Direction.fromString(parameters.getOrder());
		Sort.Order order=new Sort.Order(direction,parameters.getSort());
		orders.add(order);
		Sort sort=new Sort(orders);
		return sort;
	}
}
