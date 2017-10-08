module ModeloQytetet
  require_relative "sorpresa"
  class PruebaQytetet
    @@mazo = Array.new
    
    def self.inicializar_sorpresas
      mazo<< Sorpresa.new("Se han limpiado tus delitos de la base de datos de la policia. Sales de la cárcel", 0, TipoSorpresa::SALIRCARCEL)
      mazo<< Sorpresa.new("Te hemos pillado hackeando los servidores de la UGR,
                 vas directamente a la carcel", 9, TipoSorpresa::IRACASILLA )
      
    end
    private
    def self.get_sorpresa(tipo_sorpresa)
      m = Array.new
      for elemento in mazo
        if(elemento.tipo == tipo_sorpresa)
          m << elemento
        end
      end
    end
    def self.ir_a_casilla()
      m = Array.new
      for elemento in mazo
        if(elemento.tipo == TipoSorpresa::IRACASILLA)
          m << elemento
        end
      end
    end
    def self.mayor_que_0()
      m = Array.new
      for elemento in mazo
        if(elemento.valor > 0)
          m << elemento
        end
      end
    end
    public
    def self.main()
      m = Array.new
      inicializar_sorpresas()
      for elemento in mayor_que_0
        puts elemento.to_s
        
      end
    end
  end
end
