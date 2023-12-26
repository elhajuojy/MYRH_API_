package ma.yc.api.myrhapi;

import ma.yc.api.myrhapi.entity.JobOffer;
import ma.yc.api.myrhapi.enums.Contract;
import ma.yc.api.myrhapi.repository.JobOfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationRunner implements CommandLineRunner {

    private final JobOfferRepository jobOfferRepository;
    private Logger logger = LoggerFactory.getLogger("CommandRunner");

    public ApplicationRunner(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("TEST TEST");
        int level=1;
        for (String job : new String[]{"job-1", "job-2", "job-3", "job-5"}) {
            logger.info("SAVING NEW JOBS FROM THE RUNNER " + job);

            JobOffer jobOffer = new JobOffer();
            jobOffer.setTitle(job);
            jobOffer.setDescription("description for " + job);
            jobOffer.setContract(Contract.CDI);
            jobOffer.setEducation("BAC-"+level);
            level=level+2;
            jobOffer.setLocation("Casablanca");
            jobOffer.setSalary(10000.0);
            jobOffer.setVisibility(false);


            jobOfferRepository.save(jobOffer);
        }
    }
}
