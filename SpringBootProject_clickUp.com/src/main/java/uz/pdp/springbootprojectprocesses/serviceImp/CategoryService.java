package uz.pdp.springbootprojectprocesses.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springbootprojectprocesses.entity.Les9.Project;
import uz.pdp.springbootprojectprocesses.entity.les10_1.Category;
import uz.pdp.springbootprojectprocesses.payload.CategoryDto;
import uz.pdp.springbootprojectprocesses.payload.Result;
import uz.pdp.springbootprojectprocesses.repository.CategoryRepository;
import uz.pdp.springbootprojectprocesses.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProjectRepository projectRepository;


    // CREATE
    public Result addCategory(CategoryDto categoryDto) {

         Category category=new Category();
      if (categoryDto.getProjectId()!=null){
      Optional<Project> optionalProject = projectRepository.findById(categoryDto.getProjectId());
      if (!optionalProject.isPresent()) return new Result("such Project is not exist",false);
         category.setProject(optionalProject.get());
      }
        category.setName(categoryDto.getName());
        category.setArchived(categoryDto.isArchived());
        category.setAccessType(categoryDto.getAccessType());
        category.setColor(categoryDto.getColor());
        categoryRepository.save(category);
      return new Result("successfully saved",true);
}

    public Result editCategoryById(CategoryDto categoryDto,UUID id) {

     Optional<Category> optionalCategory = categoryRepository.findById(id);
     if (!optionalCategory.isPresent()) return new Result("such Category is not exist",false);

            Category category=optionalCategory.get();

          if (categoryDto.getProjectId()!=null){
         Optional<Project> optionalProject = projectRepository.findById(categoryDto.getProjectId());
         if (!optionalProject.isPresent()) return new Result("such Project is not exist",false);
         category.setProject(optionalProject.get());
     }
        category.setName(categoryDto.getName());
        category.setArchived(categoryDto.isArchived());
        category.setAccessType(categoryDto.getAccessType());
        category.setColor(categoryDto.getColor());
        categoryRepository.save(category);
        return new Result("successfully edited",true);
    }

    // GET
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }

    // GET BY ID
    public Category getCategoryById(UUID id){
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElseGet(Category::new);
    }

    public Result deleteCategory(UUID id){
        try {
            categoryRepository.deleteById(id);
            return new Result("category deleted",true);
        }catch (Exception e){
            return new Result("such category is not exist",false);
        }

    }

}
