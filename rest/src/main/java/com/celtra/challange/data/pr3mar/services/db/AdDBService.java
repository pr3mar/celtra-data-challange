package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.dao.AdDAO;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.AdDTO;
import com.celtra.challange.data.pr3mar.models.entity.AdEntity;
import com.celtra.challange.data.pr3mar.models.reports.AdSummary;
import com.celtra.challange.data.pr3mar.transformers.AdTransformer;
import com.celtra.challange.data.pr3mar.utils.Pair;
import com.celtra.challange.data.pr3mar.utils.Utils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@RequestScoped
public class AdDBService extends GenericDBService<AdEntity, Long, AdDTO> {

    @Inject
    protected AdDAO dao;

    @Inject
    protected AdTransformer transformer;

    @Override
    public AdDTO findById(Long id) throws EntityNotFoundException, InvalidParameterException, InternalServerExecption {
        AdEntity entity = dao.findById(id);
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<AdDTO> findAll() throws EntityNotFoundException {
        List<AdEntity> entities = dao.findAll();
        return transformer.transformToDTO(entities);
    }

    @Override
    public AdDTO createNew(AdDTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
            InvalidParameterException, InternalServerExecption
    {
        AdEntity entity = dao.createNew(
                transformer.transformToEntity(instance)
        );
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<AdDTO> createNew(List<AdDTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        List<AdDTO> created = new ArrayList<>();
        for(AdDTO dto : dtos) {
            created.add(createNew(dto));
        }
        return created;
    }

    public List<AdSummary> getAdSummaryByDate(Date dateFrom, Date dateTo) throws EntityNotFoundException {
        Pair<Date, Date> dates = Utils.getDates(dateFrom, dateTo);
        List<AdSummary> resultList = dao.getAdSummary(dates.getLeft(), dates.getRight());
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList;
    }

    public List<AdSummary> getAdSummaryByIdList(List<Long> ids) throws EntityNotFoundException {
        List<AdSummary> resultList = dao.getAdSummaryByIdList(ids);
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList;
    }

    public List<AdSummary> getAdSummaryByNameList(List<String> names) throws EntityNotFoundException {
        List<AdSummary> resultList = dao.getAdSummaryByNameList(names);
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList;
    }

    public AdSummary getAdSummaryByName(String name) throws EntityNotFoundException {
        List<String> array = new ArrayList<>();
        array.add(name);
        List<AdSummary> resultList = dao.getAdSummaryByNameList(array);
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found");
        }
        return resultList.get(0);
    }

    public AdSummary getAdSummaryById(Long id) throws EntityNotFoundException {
        List<Long> array = new ArrayList<>();
        array.add(id);
        List<AdSummary> resultList = dao.getAdSummaryByIdList(array);
        if(resultList.isEmpty()) {
            throw  new EntityNotFoundException("No impressions found.");
        }
        return resultList.get(0);
    }

    public List<AdSummary> getAdSummaryByDay(Date dateFrom, Date dateTo) throws EntityNotFoundException {
        Pair<Date, Date> dates = Utils.getDates(dateFrom, dateTo);
        List<AdSummary> resultList = dao.getReportByDay(dates.getLeft(), dates.getRight());
        if(resultList.isEmpty()) {
            throw  new EntityNotFoundException("No impressions found.");
        }
        return resultList;
    }
}

