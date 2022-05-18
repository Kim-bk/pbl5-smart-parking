package com.smartparking.service;

import java.util.List;

import com.smartparking.dto.WardDTO;


public interface IWardService {
	public List<WardDTO> findByDistrict(String id);
	public WardDTO findOneById(String id);
}
