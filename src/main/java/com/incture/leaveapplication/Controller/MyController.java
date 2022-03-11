package com.incture.leaveapplication.Controller;

import com.incture.leaveapplication.Entity.Employee;
import com.incture.leaveapplication.Entity.EmployeeWrap;
import com.incture.leaveapplication.Entity.LeaveWrap;
import com.incture.leaveapplication.Entity.EmployeeLogin;
import com.incture.leaveapplication.Entity.Leave;
import com.incture.leaveapplication.Exception.ResourceNotFoundException;
import com.incture.leaveapplication.Repository.LeaveRepository;
import com.incture.leaveapplication.Repository.MyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 @CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class MyController {

    @Autowired
    private MyRepository employeeRepository;
    @Autowired
    private LeaveRepository leaveRepository;
    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/allleaves")
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/register")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeWrap employeewrap) {
        Optional<Employee> O= Optional.ofNullable(employeeRepository.findByEmail(employeewrap.getEmail()));
        if(O.isPresent())
            return new ResponseEntity("User already exists, Please login",HttpStatus.BAD_REQUEST);
        else{
            Employee E= new Employee(employeewrap.getName(), employeewrap.getPassword(), employeewrap.getEmail());
        return ResponseEntity.ok(employeeRepository.save(E));}
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> loginEmployee(@RequestBody EmployeeLogin employeelogin) {
    Optional<Employee> O= Optional.ofNullable(employeeRepository.findByEmail(employeelogin.getEmail()));
    if(O.isPresent()) {
Employee E = O.get();
        if (E.getPassword().equals(employeelogin.getPassword())) { return ResponseEntity.ok(E);
        } else {return new ResponseEntity("Password is wrong!",HttpStatus.BAD_REQUEST);
        }
    }
    else
    {
        throw new ResourceNotFoundException("User with email "+ employeelogin.getEmail() + " not found");
    }
    }

    @PutMapping("/applyleave/{ID}")
    public boolean applyLeave(@PathVariable Long ID, @RequestBody LeaveWrap lw) {
        Optional<Employee> O = employeeRepository.findById(ID);
        Employee E = O.get();
        Leave L = null;
        if (lw.getType() == 'm')
            L = new Leave(1, lw.getType(), lw.getDescription(), E, E.getName());
        else
            L = new Leave(0, lw.getType(), lw.getDescription(), E, E.getName());


        leaveRepository.save(L);
        E.getLeaveList().add(L);

        return true;

    }

    @GetMapping("/leavestatus/{LID}")
    public int leaveStatus( @PathVariable Integer LID)
    {
        Optional<Leave> O= leaveRepository.findById(LID);
        if(O.isEmpty())
            throw new ResourceNotFoundException("Leave with ID "+ LID+ " not found");
        else
        {
            Leave L= O.get();
            return L.getLevel();
        }
    }
 
 @GetMapping("/leave/{LID}")
    public Leave leaveStatus( @PathVariable Integer LID)
    {
        Optional<Leave> O= leaveRepository.findById(LID);
        if(O.isEmpty())
            throw new ResourceNotFoundException("Leave with ID "+ LID+ " not found");
        else
        {
            Leave L= O.get();
            return L;
        }
    }

    @GetMapping("/appliedleaves/{ID}")
    public List<Leave> appliedLeaves(@PathVariable Long ID)
    {
        Optional<Employee> O= employeeRepository.findById(ID);

        if(O.isEmpty())
            throw new ResourceNotFoundException("Employee with ID "+ ID+ " not found");
        else
        {
            Employee E= O.get();
            return E.getLeaveList();
            //return leaveRepository.findAll();
        }
    }

    @PutMapping("/changestatusHR/{LID}")
    public Leave changeStatusHR(@PathVariable Integer LID)
    {
        Optional<Leave> O= leaveRepository.findById(LID);
        if(O.isEmpty())
            throw new ResourceNotFoundException("Leave with ID "+ LID+ " not found");
        else
        {
            Leave L= O.get();
             L.setLevel(2);
             return leaveRepository.save(L);
        }
    }
@PutMapping("/denyleave/{LID}")
    public Leave denyLeave(@PathVariable Integer LID)
    {
        Optional<Leave> O= leaveRepository.findById(LID);
        if(O.isEmpty())
            throw new ResourceNotFoundException("Leave with ID "+ LID+ " not found");
        else
        {
            Leave L= O.get();
             L.setLevel(-1);
             return leaveRepository.save(L);
        }
    }

    @PutMapping("/changestatusManager/{LID}")
    public Leave changeStatusManager(@PathVariable Integer LID)
    {
        Optional<Leave> O= leaveRepository.findById(LID);
        if(O.isEmpty())
            throw new ResourceNotFoundException("Leave with ID "+ LID+ " not found");
        else
        {
            Leave L= O.get();
            L.setLevel(1);
            return leaveRepository.save(L);
        }
    }

    @GetMapping("/leavesforManager")
    public List<Leave> getLeavesforManager() {
        return leaveRepository.findLeavesforManager();
    }

    @GetMapping("/leavesforHR")
    public List<Leave> getLeavesforHR() {
        return leaveRepository.findLeavesforHR();
    }

    @GetMapping("/loginManager")
    public ResponseEntity<String> loginManager(@RequestParam String Username, @RequestParam String Pass) {
        if(Username.equals("Manager101")&&Pass.equals("managerpass"))
            return ResponseEntity.ok("Manager Login Successful");
        else
            return new ResponseEntity<>("Manager Login Failed",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/loginHR")
    public ResponseEntity<String> loginHR(@RequestParam String Username, @RequestParam String Pass) {
        if(Username.equals("HR101")&&Pass.equals("HRpass"))
            return ResponseEntity.ok("HR Login Successful");
        else
            return new ResponseEntity<>("HR Login Failed",HttpStatus.BAD_REQUEST);
    }
    // get employee by id rest api
   /* @GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable Long id) {
        Employee employee;
        employee = employeeRepository.findById(id)
                .orElseThrow(() - > new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }*/

    // update employee rest api

    /*@PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() - > new ResourceNotFoundException("Employee not exist with id :" + id));

        employee.setName(employeeDetails.getName());

        employee.setEmail(employeeDetails.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }*/

    // delete employee rest api
   /* @DeleteMapping("/employees/{id}")
    public ResponseEntity <Map< String, Boolean >> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() - > new ResourceNotFoundException("Employee not exist with id :" + id));

        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }*/
}
