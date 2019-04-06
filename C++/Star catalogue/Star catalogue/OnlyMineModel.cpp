#include "OnlyMineModel.h"
#include <qfont.h>
#include <qbrush.h>
#include <qcolor.h>

using namespace std;

OnlyMineModel::OnlyMineModel(Astronomer& a, RepoStars& s, QObject *parent) : astro(a), stars(s), QAbstractTableModel(parent)
{
}

OnlyMineModel::~OnlyMineModel()
{
}

int OnlyMineModel::columnCount(const QModelIndex & parent) const
{
	return 4;
}

int OnlyMineModel::rowCount(const QModelIndex & parent) const
{
	return stars.getStars().size() + 1;
}

QVariant OnlyMineModel::data(const QModelIndex & index, int role) const
{
	int row = index.row(), col = index.column();

	if (row == stars.getStars().size())
		return QVariant();

	Star s = stars.getStars()[row];
	if(astro.getConstellation().compare(s.getConstellation())==0)
		if (role == Qt::DisplayRole || role == Qt::EditRole)
		{
			switch (col)
			{
			case 0:
				return QString::fromStdString(s.getName());
			case 1:
				return QString::fromStdString(s.getConstellation());
			case 2:
				return QString::number(s.getLuminosity());
			case 3:
				return QString::number(s.getTemperature());
			default:
				break;
			}
		}

	if (role == Qt::FontRole)
		return QFont("Times", 12, 10, false);

	return QVariant();
}

QVariant OnlyMineModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (orientation == Qt::Horizontal)
		if (role == Qt::DisplayRole || role == Qt::EditRole)
		{
			switch (section)
			{
			case 0:
				return QString("Name");
			case 1:
				return QString("Constellation");
			case 2:
				return QString("Luminosity");
			case 3:
				return QString("Temperature");
			default:
				break;
			}
		}

	if (role == Qt::FontRole)
		return QFont("Times", 16, 14, false);

	return QVariant();
}
