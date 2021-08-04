package br.com.pedidovenda.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.com.pedidovenda.modelo.Usuario;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.security.UsuarioLogado;
import br.com.pedidovenda.security.UsuarioSistema;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private LineChartModel model;

	public void preRender() {
		this.model = new LineChartModel();
		
		model.setAnimate(true);
		model.setTitle("Pedidos Realizados");
		model.setLegendPosition("e");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Dia"));
		
		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
	}

	private void adicionarSerie(String rotulo, Usuario criadoPor) {
		Map<Date, BigDecimal> valoresPorData = this.pedidos.valoresTotaisPorData(15, criadoPor);
		
		ChartSeries series = new ChartSeries();
        series.setLabel(rotulo);
		
		for(Date data : valoresPorData.keySet()){
			series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}
		
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setMin(0);
		
        yAxis.setLabel("Pedidos");
        
        model.addSeries(series);
	}
	
	public LineChartModel getModel() {
		return model;
	}

}
