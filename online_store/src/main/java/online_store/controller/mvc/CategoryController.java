package online_store.controller.mvc;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online_store.domain.dto.CategoryDTO;
import online_store.exception.ShopException;
import online_store.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final ShopService shopService;

    @GetMapping
    public String getCategories(Model model) {
        prepareModelForForm(model, new CategoryDTO(), "");
        return "categories";
    }


    @PostMapping
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO, Model model) {
        try {
            shopService.saveOrUpdateCategoryWithoutParent(categoryDTO);
        } catch (ShopException e) {
            prepareModelForForm(model, categoryDTO, e.getMessage());
            return "categories";
        }
        return "redirect:/category";
    }

    private Model prepareModelForForm(Model model, CategoryDTO categoryDTO, String errors) {
        model.addAttribute("category", categoryDTO);
        model.addAttribute("errors", errors);
        model.addAttribute("categories", shopService.getAllCategories());
        return model;
    }
}