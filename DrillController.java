package org.bisag.ocbis.controllers;

import org.bisag.ocbis.models.DrillingUnitDeployment;
import org.bisag.ocbis.models.EngineDetails;
import org.bisag.ocbis.models.PumpDetails;
import org.bisag.ocbis.models.PumpEngineDetails;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.bisag.ocbis.models.DailyProgressDowntimeReason;
import org.bisag.ocbis.models.DailyProgressFormation;
import org.bisag.ocbis.payloads.request.EncryptedRequest;
import org.bisag.ocbis.payloads.request.GetId;
import org.bisag.ocbis.payloads.request.Report;
import org.bisag.ocbis.payloads.response.EncryptedResponse;
import org.bisag.ocbis.repository.DailyProgressDowntimeReasonRepository;
import org.bisag.ocbis.repository.DailyProgressFormationRepository;
import org.bisag.ocbis.repository.DrillRepo;
import org.bisag.ocbis.repository.DrillingUnitDeploymentRepository;
import org.bisag.ocbis.repository.EngineDetailsRepository;
import org.bisag.ocbis.repository.PumpDetailsRepository;
import org.bisag.ocbis.repository.PumpEngineDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.Transient;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/drill")
public class DrillController {

  @Autowired
  DrillingUnitDeploymentRepository drillRepo;

  // @Autowired
  // DailyProgressDetailsRepository dailyProgRepo;

  @Autowired
  private DailyProgressFormationRepository dailyProgressFormationRepo;

  @Autowired
  private DailyProgressDowntimeReasonRepository dailyProgressDowntimeReasonRepo;

  // @Autowired
  // DrillRigsRepository drillrigsRepo;

  @Autowired
  DrillRepo drilllRepo;

  @Autowired
  EngineDetailsRepository engineDetailsRepository;

  @Autowired
  PumpDetailsRepository pumpDetailsRepository;

  @Autowired
  PumpEngineDetailsRepository pumpenginedsetailsRepository;

  @PostMapping("/save-drill-unit-deployment")
  public <json> EncryptedResponse saveDrillUnit(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {

    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    Map<String, Object> responseMap = new HashMap<>();
    try {
      if (body.getId() != null) {
        // This is a new record
        savedBody = body;
      } else {
        // Attempt to find the existing entity
        DrillingUnitDeployment existing = drillRepo.findByFormId(body.getId());
        System.out.println("existing:" + existing);
        // Check if the existing entity was found
        if (existing != null) {
          return new EncryptedResponse("No drilling unit found with the provided ID.");
        }
        // Update the existing entity's fields
        existing.setFspYear(body.getFspYear());
        existing.setRegion(body.getRegion());
        existing.setFspCode(body.getFspCode());
        existing.setMission(body.getMission());
        existing.setTypeOfSource(body.getTypeOfSource());
        existing.setUnitNumber(body.getUnitNumber());
        existing.setModel(body.getModel());
        existing.setClearanceRequired(body.getClearanceRequired());
        existing.setOutSourcingAgency(body.getOutSourcingAgency());
        existing.setAreaOfOperation(body.getAreaOfOperation());
        existing.setRemarks(body.getRemarks());
        existing.setTotalDrillingQuantum(body.getTotalDrillingQuantum());
        existing.setFspTarget(body.getFspTarget());
        existing.setUnitTarget(body.getUnitTarget());
        existing.setFspProjectincharge(body.getFspProjectincharge());
        existing.setAreaIncharge(body.getAreaIncharge());
        existing.setUnitIncharge(body.getUnitIncharge());
        existing.setAsstOfficer(body.getAsstOfficer());
        // Save the updated existing entity
        savedBody = drillRepo.save(existing);
      }
      savedBody = drillRepo.save(savedBody);
      // Prepare the response
      responseMap.put("id", savedBody.getId());
      responseMap.put("message", "Saved"); 
    } catch (Exception e) {
      return new EncryptedResponse(e);
    }

    return new EncryptedResponse(responseMap);
  }

  @PostMapping("/get-drill-details-form-wise")
  public EncryptedResponse getAlreadyFilledDetailsFormWise(@RequestBody EncryptedRequest req)
      throws Exception {
    var body = req.bodyAs(GetId.class);
    Long id = Long.parseLong(body.id());
    List<Map<String, Object>> fspDetail = drillRepo.getAlreadyFilledDetailsFormWise(id);
    return new EncryptedResponse(fspDetail);
  }

  @Transient
  @PostMapping("/save-drill_daily_progress_details")
  public EncryptedResponse saveDrillDailyProgress(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {
    // var body = req.bodyAs(DrillRigs.class);
    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    try {
      if (body.getId() != null) {
        DrillingUnitDeployment existing = drillRepo.findByFormId(body.getId());

        if (existing == null) {
          return new EncryptedResponse("No drilling unit found with the provided ID.");
        }

        existing.setUnitNum(body.getUnitNum());
        existing.setAreaOfOper(body.getAreaOfOper());
        existing.setBoreStartDate(body.getBoreStartDate());
        existing.setModel(body.getModel());
        existing.setMineral(body.getMineral());
        existing.setDrillEndDate(body.getDrillEndDate());
        existing.setBasicDrillFspCode(body.getBasicDrillFspCode());
        existing.setBoreHoleNumber(body.getBoreHoleNumber());
        existing.setBoreCloseDate(body.getBoreCloseDate());
        existing.setBasicDrillMission(body.getBasicDrillMission());
        existing.setAngle(body.getAngle());
        existing.setBoreSiteCloseDate(body.getBoreSiteCloseDate());
        existing.setBasicDrillRegion(body.getBasicDrillRegion());
        existing.setElevation(body.getElevation());
        existing.setUnitInChar(body.getUnitInChar());
        existing.setStateUnit(body.getStateUnit());
        existing.setBearing(body.getBearing());
        existing.setAsstOff(body.getAsstOff());
        existing.setMineTectBeltBasin(body.getMineTectBeltBasin());
        existing.setBoreAllDate(body.getBoreAllDate());
        savedBody = drillRepo.save(existing);
      } else {
        savedBody = drillRepo.save(body);
      }
    } catch (Exception e) {
      return new EncryptedResponse(e);
    }
    return new EncryptedResponse("success");
  }

  @Transient
  @PostMapping("/save-daily-shift-details")
  public EncryptedResponse saveDailShift(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {
    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    try {
      if (body.getId() != null) {
        DrillingUnitDeployment existing = drillRepo.findByFormId(body.getId());

        if (existing == null) {
          return new EncryptedResponse("No drilling unit found with the provided ID.");
        }
        existing.setTypeOfShift(body.getTypeOfShift());
        existing.setShiftDate(body.getShiftDate());
        existing.setTypeOfDrill(body.getTypeOfDrill());
        existing.setShiftDetails(body.getShiftDetails());
        existing.setDepthFrom(body.getDepthFrom());
        existing.setDepthTo(body.getDepthTo());
        existing.setCoreRecovery(body.getCoreRecovery());
        existing.setRshellSize(body.getRshellSize());
        existing.setBitType(body.getBitType());
        existing.setBitSize(body.getBitSize());
        existing.setBitNum(body.getBitNum());
        existing.setRshellNum(body.getRshellNum());
        existing.setStrtDate(body.getStrtDate());
        existing.setEndDate(body.getEndDate());
        existing.setDailyShiftremarks(body.getDailyShiftremarks());
        savedBody = drillRepo.save(existing);
      } else {
        savedBody = drillRepo.save(body);
      }
    } catch (Exception e) {
      return new EncryptedResponse(e);
    }

    for (DailyProgressFormation dailyProgressFormation : body.getAddFormation()) {
      DailyProgressFormation dpFormation = new DailyProgressFormation();
      dpFormation.setFormationType(dailyProgressFormation.getFormationType());
      dpFormation.setDrillUnitId(savedBody.getId()); // Use the ID of the saved drillpro
      dailyProgressFormationRepo.save(dpFormation);
    }
    for (DailyProgressDowntimeReason dailyProgressDowntimeReason : body.getDownReason()) {
      DailyProgressDowntimeReason dpDowntimeReason = new DailyProgressDowntimeReason();
      dpDowntimeReason.setDownReason(dailyProgressDowntimeReason.getDownReason());
      dpDowntimeReason.setNumOfHrs(dailyProgressDowntimeReason.getNumOfHrs());
      dpDowntimeReason.setRemark(dailyProgressDowntimeReason.getRemark());
      dpDowntimeReason.setDrillUnitId(savedBody.getId());
      dailyProgressDowntimeReasonRepo.save(dpDowntimeReason);
    }

    return new EncryptedResponse("success");
  }

  // @PostMapping("/get-dailyprogress-detail-formwise")
  // public EncryptedResponse getAlreadyFilledDailyprogressDetailsFormWise(@RequestBody EncryptedRequest req)
  //     throws Exception {
  //   var body = req.bodyAs(GetId.class);
  //   Long id = Long.parseLong(body.id());
  //   List<Map<String, Object>> dpDetail = dailyProgRepo.getAlreadyFilledDailyprogressDetails(id);
  //   return new EncryptedResponse(dpDetail);
  // }

  @Transient
  @PostMapping("/save-drill-rigs-details")
  public <json> EncryptedResponse saveDrillRigs(@RequestBody EncryptedRequest req,
      HttpServletResponse response) throws Exception {
    var body = req.bodyAs(DrillingUnitDeployment.class);

    DrillingUnitDeployment savedBody;

    try {
      if (body.getId() != null) {

        DrillingUnitDeployment existing = drillRepo.findByFormId(body.getId());
        if (existing == null) {
          return new EncryptedResponse("No drilling unit found with the provided ID.");
        }
        existing.setRegionOthers(body.getRegionOthers());
        existing.setMachineSinumber(body.getMachineSinumber());
        existing.setTypeOfRig(body.getTypeOfRig());
        existing.setStatus(body.getStatus());
        existing.setDescription(body.getDescription());
        existing.setBookValue(body.getBookValue());
        existing.setUnitNumber(body.getUnitNumber());
        existing.setInitialDateOfCommissioning(body.getInitialDateOfCommissioning());
        existing.setIvNumber(body.getIvNumber());
        existing.setDisposalDetails(body.getDisposalDetails());
        existing.setIvDate(body.getIvDate());
        existing.setHoursTill31stMarch2017(body.getHoursTill31stMarch2017());
        existing.setMake(body.getMake());
        existing.setExpenditureTill31stMarch2017(body.getExpenditureTill31stMarch2017());
        existing.setModel(body.getModel());
        existing.setDateOfWithdrawal(body.getDateOfWithdrawal());
        existing.setRatedCapacity(body.getRatedCapacity());
        existing.setWorkingCapacity(body.getWorkingCapacity());
        existing.setPresentDeployment(body.getPresentDeployment());
        savedBody = drillRepo.save(existing);
      } else {
        savedBody = drillRepo.save(body);
      }
    } catch (Exception e) {
      return new EncryptedResponse(e);
    }

    for (EngineDetails engineDetails : body.getEnginedetails()) {
      EngineDetails engines = new EngineDetails();
      engines.setEngineSerialNumber(engineDetails.getEngineSerialNumber());
      engines.setDescription(engineDetails.getDescription());
      engines.setInitialDateOfCommissioning(engineDetails.getInitialDateOfCommissioning());
      engines.setDateOfWithdrawal(engineDetails.getDateOfWithdrawal());
      engines.setMake(engineDetails.getMake());
      engines.setRating(engineDetails.getRating());

      engines.setIvNumber(engineDetails.getIvNumber());
      engines.setIvDate(engineDetails.getIvDate());

      engines.setDrillUnitId(savedBody.getId());
      engineDetailsRepository.save(engines);
    }

    for (PumpDetails pumpDetails : body.getPumpdetails()) {
      PumpDetails pumps = new PumpDetails();
      pumps.setDescription(pumpDetails.getDescription());
      pumps.setInitialDateOfCommissioning(pumpDetails.getInitialDateOfCommissioning());
      pumps.setDateOfWithdrawal(pumpDetails.getDateOfWithdrawal());
      pumps.setMake(pumpDetails.getMake());
      pumps.setRating(pumpDetails.getRating());
      pumps.setDepartmentNumber(pumpDetails.getDepartmentNumber());
      pumps.setPumpSerialNumber(pumpDetails.getPumpSerialNumber());
      pumps.setModel(pumpDetails.getModel());
      pumps.setIvNumber(pumpDetails.getIvNumber());
      pumps.setDisposalDetails(pumpDetails.getDisposalDetails());
      pumps.setDrillexpenditureTill31stMarch(pumpDetails.getDrillexpenditureTill31stMarch());
      pumps.setDrillhoursTill31stMarch(pumpDetails.getDrillhoursTill31stMarch());
      pumps.setIvDate(pumpDetails.getIvDate());
      pumps.setDrillUnitId(savedBody.getId());
      pumpDetailsRepository.save(pumps);
    }

    for (PumpEngineDetails pumpEngineDetails : body.getPumpengineDetails()) {
      PumpEngineDetails pumpengine = new PumpEngineDetails();
      pumpengine.setPumpSerialNumber(pumpEngineDetails.getPumpSerialNumber());
      pumpengine.setEngineSerialNumber(pumpEngineDetails.getEngineSerialNumber());
      pumpengine.setEngineDescription(pumpEngineDetails.getEngineDescription());
      pumpengine.setEngineMake(pumpEngineDetails.getEngineMake());
      pumpengine.setEngineModel(pumpEngineDetails.getEngineModel());
      pumpengine.setEngineIvNumber(pumpEngineDetails.getEngineIvNumber());
      pumpengine.setEngineIvDate(pumpEngineDetails.getEngineIvDate());
      pumpengine.setEngineRating(pumpEngineDetails.getEngineRating());
      pumpengine.setEngineInitialDateOfCommissioning(pumpEngineDetails.getEngineInitialDateOfCommissioning());
      pumpengine.setEngineHoursTill31stMarch(pumpEngineDetails.getEngineHoursTill31stMarch());
      pumpengine.setEngineExpenditureTill31stMarch(pumpEngineDetails.getEngineExpenditureTill31stMarch());
      pumpengine.setEngineDateOfWithdrawal(pumpEngineDetails.getEngineDateOfWithdrawal());
      pumpengine.setDrillUnitId(savedBody.getId());
      pumpenginedsetailsRepository.save(pumpengine);
    }

    return new EncryptedResponse("success");
  }

  @PostMapping("/get-dril-request-report")
  public EncryptedResponse getFspForPeerReview(@RequestBody EncryptedRequest req)
      throws Exception {

    var body = req.bodyAs(Report.class);
    var pageable = PageRequest.of(body.pagination().page(), body.pagination().size());
    Page<Map<String, Object>> result = drilllRepo.getDrillRequestReview(pageable);
    return new EncryptedResponse(result);
  }

  @PostMapping("/get-region-name")
  public <json> EncryptedResponse getRegionName(
  HttpServletResponse response) throws Exception {
  List<Map<String, Object>> result = drillRepo.getRegionName();
  return new EncryptedResponse(result);
  }
}
