# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  require 'singleton'
  class Dado
    include Singleton
    def initialize     
    end
    def tirar()
      rand(1..6)
    end
  end
end
