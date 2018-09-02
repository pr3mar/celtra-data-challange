package com.celtra.challange.data.pr3mar.services.db;

import com.celtra.challange.data.pr3mar.dao.AdDAO;
import com.celtra.challange.data.pr3mar.dao.CampaignDAO;
import com.celtra.challange.data.pr3mar.exceptions.EntityNotFoundException;
import com.celtra.challange.data.pr3mar.exceptions.InternalServerExecption;
import com.celtra.challange.data.pr3mar.exceptions.InvalidEntityException;
import com.celtra.challange.data.pr3mar.exceptions.InvalidParameterException;
import com.celtra.challange.data.pr3mar.models.dto.CampaignDTO;
import com.celtra.challange.data.pr3mar.models.entity.CampaignEntity;
import com.celtra.challange.data.pr3mar.models.reports.CampaignSummary;
import com.celtra.challange.data.pr3mar.transformers.CampaignTransformer;
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
public class CampaignDBService extends GenericDBService<CampaignEntity, Long, CampaignDTO> {

    @Inject
    private CampaignDAO dao;

    @Inject
    private AdDAO adDAO;

    @Inject
    protected CampaignTransformer transformer;

    @Override
    public CampaignDTO findById(Long id) throws EntityNotFoundException, InvalidParameterException, InternalServerExecption {
        CampaignEntity entity = dao.findById(id);
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<CampaignDTO> findAll() throws EntityNotFoundException {
        List<CampaignEntity> entities = dao.findAll();
        return transformer.transformToDTO(entities);
    }

    @Override
    public CampaignDTO createNew(CampaignDTO instance)
            throws  InvalidEntityException, EntityNotFoundException,
            InvalidParameterException, InternalServerExecption
    {
        CampaignEntity entity = dao.createNew(
                transformer.transformToEntity(instance)
        );
        return transformer.transformToDTO(entity);
    }

    @Override
    public List<CampaignDTO> createNew(List<CampaignDTO> dtos)
            throws  InternalServerExecption, InvalidEntityException,
                    InvalidParameterException, EntityNotFoundException
    {
        List<CampaignDTO> created = new ArrayList<>();
        for(CampaignDTO dto : dtos) {
            created.add(createNew(dto));
        }
        return created;
    }

    public CampaignSummary getCampaignReportById(long id) throws EntityNotFoundException {
        List<Long> array = new ArrayList<>();
        array.add(id);
        List<CampaignSummary> resultList = dao.getReportByIdList(array);
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList.get(0);
    }


    public List<CampaignSummary> getCampaignReportByIdList(List<Long> ids)
            throws EntityNotFoundException {
        List<CampaignSummary> resultList = dao.getReportByIdList(ids);
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList;
    }

    public CampaignSummary getCampaignReportByName(String name) throws EntityNotFoundException {
        List<String> array = new ArrayList<>();
        array.add(name);
        List<CampaignSummary> resultList = dao.getReportByNameList(array);
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList.get(0);
    }

    public List<CampaignSummary> getCampaignReportByNameList(List<String> names)
            throws EntityNotFoundException {
        List<CampaignSummary> resultList = dao.getReportByNameList(names);
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList;
    }

    public List<CampaignSummary> getCampaignReportsByDate(
            Date dateFrom, Date dateTo
    ) throws EntityNotFoundException {
        Pair<Date, Date> dates = Utils.getDates(dateFrom, dateTo);
        List<CampaignSummary> resultList = dao.getReportsByDate(dates.getLeft(), dates.getRight());
        if(resultList.isEmpty()) {
            throw new EntityNotFoundException("No impressions found.");
        }
        return resultList;
    }

    public CampaignSummary getCampaignReportWithAds(
            Long id
    ) throws EntityNotFoundException {
        CampaignSummary summary = getCampaignReportById(id);
        if(summary == null) {
            throw new EntityNotFoundException("Impressions not found.");
        }
        summary.setAdSummaries(adDAO.getAdSummaryByCampaignId(id));
        return summary;
    }

    public List<CampaignSummary> getCampaignReportByDay(Date dateFrom, Date dateTo)
            throws EntityNotFoundException {
        Pair<Date, Date> dates = Utils.getDates(dateFrom, dateTo);
        List<CampaignSummary> summary = dao.getReportByDay(dates.getLeft(), dates.getRight());
        if(summary.isEmpty()) {
            throw new EntityNotFoundException("Impressions not found.");
        }
        return summary;
    }

    public List<CampaignSummary> getOngoingCampaignReport()
            throws EntityNotFoundException {
        List<CampaignSummary> summary = dao.getReportOngoing();
        if(summary.isEmpty()) {
            throw new EntityNotFoundException("Impressions not found.");
        }
        return summary;
    }

    public List<CampaignSummary> getOngoingCampaignReportByDay()
            throws EntityNotFoundException {
        List<CampaignSummary> summary = dao.getReportOngoingByDay();
        if(summary.isEmpty()) {
            throw new EntityNotFoundException("Impressions not found.");
        }
        return summary;
    }

    public List<CampaignSummary> getFinishedCampaignReport()
            throws EntityNotFoundException {
        List<CampaignSummary> summary = dao.getReportFinished();
        if(summary.isEmpty()) {
            throw new EntityNotFoundException("Impressions not found.");
        }
        return summary;
    }

    public List<CampaignSummary> getFinishedCampaignReportByDay()
            throws EntityNotFoundException {
        List<CampaignSummary> summary = dao.getReportFinishedByDay();
        if(summary.isEmpty()) {
            throw new EntityNotFoundException("Impressions not found.");
        }
        return summary;
    }
}

