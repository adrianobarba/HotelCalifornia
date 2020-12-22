package  com.hotelCalifornia.hotelCalifornia.controller ;
		
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelCalifornia.hotelCalifornia.model.HotelCalifornia;
import com.hotelCalifornia.hotelCalifornia.repository.HotelCalifornicaRepository;

@RestController
@RequestMapping({ "/api/hotel" })
public class hotelCaliforniaController {

	
	private final HotelCalifornicaRepository repository;
	
	@Autowired
	public hotelCaliforniaController(HotelCalifornicaRepository repository) {
		this.repository = repository;
	}


	@GetMapping
//	http://localhost:8090/hotelCalifornia
	public List<HotelCalifornia> findAll() {
		return repository.findAll();
	}


	@GetMapping(value = "{id}")
	public ResponseEntity<HotelCalifornia> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}


	@PostMapping
	public HotelCalifornia create(@RequestBody HotelCalifornia hotelCalifornia) {
		return repository.save(hotelCalifornia);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<HotelCalifornia> update(@PathVariable long id, @RequestBody HotelCalifornia hotelCalifornia) {
		return repository.findById(id).map(salvar -> {
			salvar.setName(hotelCalifornia.getName());
			salvar.setCapacidade(hotelCalifornia.getCapacidade());
			salvar.setLocal(hotelCalifornia.getLocal());
			HotelCalifornia update = repository.save(salvar);

			return ResponseEntity.ok().body(update);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(deletar -> {
					repository.deleteById(id);
										
					return ResponseEntity.ok().body("Cliente " + deletar.getName() + " deletado com sucesso!");
				}).orElse(ResponseEntity.ok().body("Cliente não localizado"));
	}
	

}
