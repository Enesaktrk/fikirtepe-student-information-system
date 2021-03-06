package com.fikirtepe.app.Service.CityServiceImplemtation;

import com.fikirtepe.app.Model.City;
import com.fikirtepe.app.Model.District;
import com.fikirtepe.app.Repository.CityRepository;
import com.fikirtepe.app.Repository.DistrictRepository;
import com.fikirtepe.app.Service.CityAndDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CityAndDistrictServiceImplementation implements CityAndDistrictService {

    DistrictRepository districtRepository;
    @Autowired
    public void setDistrictRepository(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    private CityRepository cityRepository;
    @Autowired
    public void setCityRepository(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<District> findAllDistricts(Long cityPlateNo){
       return districtRepository.findAllByCityPlateNo(cityPlateNo);
    }

    @Override
    public List<City> findAllCities(){
//        return entityManager.createQuery("select cityName from City").getResultList();
        return cityRepository.findAll();
    }
}
