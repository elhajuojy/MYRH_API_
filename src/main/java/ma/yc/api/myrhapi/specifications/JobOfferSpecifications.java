package ma.yc.api.myrhapi.specifications;

import ma.yc.api.myrhapi.entity.JobOffer;
import org.springframework.data.jpa.domain.Specification;

public class JobOfferSpecifications {
    public static Specification<JobOffer> titleLike(String title){
        return (root, query, criteriaBuilder)->criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),"%"+title.toLowerCase()+"%");

    }

    public static Specification<JobOffer> educationLike(String education){
        return (root, query, criteriaBuilder)->criteriaBuilder.like(
                criteriaBuilder.lower(root.get("education")),"%"+education.toLowerCase()+"%");

    }


    public static Specification<JobOffer> locationLike(String location){
        return (root, query, criteriaBuilder)->criteriaBuilder.like(
                criteriaBuilder.lower(root.get("location")),"%"+location.toLowerCase()+"%");

    }

    public static Specification<JobOffer> visibilityIs(String visibility){
        return (root, query, criteriaBuilder)->criteriaBuilder.equal(
                root.get("visibility"),Boolean.parseBoolean(visibility));

    }
}
