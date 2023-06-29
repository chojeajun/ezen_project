

package com.ticket.t1.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ticket.t1.dto.BannerVO;
import com.ticket.t1.dto.ContentVO;


package com.ticket.t1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IContentDao {

	void getBestNewSuccessBannerList(HashMap<String, Object> paramMap);


}
