/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import itucs.blg361e.courseautomation.model.Course;
import itucs.blg361e.courseautomation.model.CourseCollectionJDBC;
import itucs.blg361e.courseautomation.model.OpenCourseCollectionJDBC;
import itucs.blg361e.courseautomation.utility.SelectOption;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author Berker
 */
public final class CourseSelectPage extends BasePage {
    
    class CourseSelectForm extends Form {
        
        class CourseSelectFormModel {
            private SelectOption selection;
            
            CourseSelectFormModel() {
                
            }

            public SelectOption getSelection() {
                return selection;
            }

            public void setSelection(SelectOption selection) {
                this.selection = selection;
            }
        }
        
        private DropDownChoice<Course> ddc;
        public CourseSelectForm(String property) {
            super(property);
            CompoundPropertyModel model = new CompoundPropertyModel(new CourseSelectFormModel());
            this.setModel(model);
            final CourseCollectionJDBC cCollection = new CourseCollectionJDBC();
            List<SelectOption> selectChoices = new ArrayList<SelectOption>();
            for (Course crs : cCollection.getCourses()) {
                selectChoices.add(new SelectOption(crs.getId().toString(), crs.getName() + " (" + crs.getCode() + ")"));
            }
            ChoiceRenderer cr = new ChoiceRenderer("value", "key");
            ddc = (DropDownChoice<Course>) new DropDownChoice("selection", selectChoices, cr).setRequired(true);
            add(ddc);
            cCollection.close();
            
            Button edit = new Button("button_edit") {
                @Override
                public void onSubmit() {
                    CourseSelectFormModel formResult = (CourseSelectFormModel) CourseSelectForm.this.getModelObject();
                    if (!"0".equals(formResult.getSelection().getKey())) {
                        PageParameters pageParameters = new PageParameters();
                        pageParameters.add("id", formResult.getSelection().getKey());
                        this.setResponsePage(CourseEditPage.class, pageParameters);
                    }
                }
            };
            add(edit);
            
            Button delete = new Button("button_delete") {
                @Override
                public void onSubmit() {
                    CourseSelectFormModel formResult = (CourseSelectFormModel) CourseSelectForm.this.getModelObject();
                    if (formResult.getSelection() != null) {
                        CourseCollectionJDBC cCollection = new CourseCollectionJDBC();
                        cCollection.deleteCourseByID(Integer.parseInt(formResult.getSelection().getKey()));
                        info("Course is deleted!");
                        cCollection.close();
                    } else {
                        error("Select a course to delete!");
                    }
                    refreshDropdown();
                }
            };
            add(delete);
        }
        
        public void refreshDropdown() {
            final CourseCollectionJDBC cCollection = new CourseCollectionJDBC();
            List<SelectOption> selectChoices = new ArrayList<SelectOption>();
            for (Course crs : cCollection.getCourses()) {
                selectChoices.add(new SelectOption(crs.getId().toString(), crs.getName() + " (" + crs.getCode() + ")"));
            }
            ChoiceRenderer cr = new ChoiceRenderer("value", "key");
            ddc = (DropDownChoice<Course>) new DropDownChoice("selection", selectChoices, cr).setRequired(true);
            cCollection.close();
            replace(ddc);
        }

        
    }

    public CourseSelectPage() {
        replace(new HeaderPanel("headerpanel","Course Selection"));
        CourseSelectForm form = new CourseSelectForm("course_select");
        add(form);
        
    }
}
